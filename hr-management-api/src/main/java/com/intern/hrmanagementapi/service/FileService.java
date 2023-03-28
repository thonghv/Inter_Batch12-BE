package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.FileEntity;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ObjectException;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.model.FileResponseDto;
import com.intern.hrmanagementapi.repo.FileRepo;
import com.intern.hrmanagementapi.repo.UserRepo;
import com.intern.hrmanagementapi.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * File service.
 */
@Service
@RequiredArgsConstructor
public class FileService {

  @Autowired
  private final FileRepo fileRepo;
  @Autowired
  private final UserRepo userRepo;

  /**
   * Store a list of files to DB
   *
   * @param files the list of files
   * @return the data response dto
   * @throws IOException the io exception
   */
  public DataResponseDto storeAll(List<MultipartFile> files) throws IOException {
    DataResponseDto response;

    if (files.size() == 0) {
      response = DataResponseDto.error(HttpStatus.BAD_REQUEST.value(),
          HttpStatus.BAD_REQUEST.name(), String.format("%s", MessageConst.File.UPLOAD_EMPTY));
      return response;
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity loggingUser = userRepo.findByEmail(auth.getName()).get();

    List<String> filenames = new ArrayList<>();
    List<FileEntity> storedFiles = new ArrayList<>();

    for (MultipartFile file : files) {
      String filename = file.getOriginalFilename();
      FileEntity newFile;

      if (fileRepo.existsByName(filename)) {
        newFile = fileRepo.findByName(filename).orElse(null);
      } else {

        newFile = FileEntity.builder().name(filename).type(file.getContentType())
            .data(file.getBytes()).userId(loggingUser.getId()).createdAt(new Date()).build();
      }
      filenames.add(file.getOriginalFilename());
      storedFiles.add(newFile);
    }
    fileRepo.saveAll(storedFiles);
    response = DataResponseDto.success(HttpStatus.OK.value(), MessageConst.File.UPLOAD_DONE, null);
    return response;
  }

  /**
   * Get file by ID
   *
   * @param fileId the file id
   * @return the file or null
   * @throws ObjectNotFoundException the object not found exception
   */
  public FileResponseDto getFile(UUID fileId) throws ObjectException {
    FileEntity resFile = fileRepo.findById(fileId).orElse(null);
    if (resFile == null) {
      throw new ObjectException(MessageConst.File.NOT_EXIST, HttpStatus.BAD_REQUEST, null);
    }
    String fileDownloadUri = FileUtil.getFileDownloadUri(resFile.getId().toString());
    var res = new FileResponseDto(resFile.getName(), fileDownloadUri, resFile.getType(),
        resFile.getData().length, resFile.getCreatedAt());
    return res;
  }


  /**
   * Gets file by id.
   *
   * @param fileId the file id
   * @return the file
   * @throws ObjectNotFoundException the object not found exception
   */
  public FileEntity getFileById(UUID fileId) throws ObjectNotFoundException {
    FileEntity resFile = fileRepo.findById(fileId).orElse(null);
    if (resFile == null) {
      throw new ObjectException(MessageConst.File.NOT_EXIST, HttpStatus.BAD_REQUEST, null);
    }
    return resFile;
  }

  /**
   * Gets all file in DB
   *
   * @return the list of all files
   */
  public Object getAllFile() {
    List<FileResponseDto> responseFiles = fileRepo.findAll().stream().map(file -> {
      String fileDownloadUri = FileUtil.getFileDownloadUri(file.getId().toString());
      return new FileResponseDto(file.getName(), fileDownloadUri, file.getType(),
          file.getData().length, file.getCreatedAt());
    }).collect(Collectors.toList());
    return responseFiles;
  }
}
