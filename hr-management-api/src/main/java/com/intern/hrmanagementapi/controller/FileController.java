package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.service.FileService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {EndpointConst.FILE_BASE_PATH})
@Tag(name = "File", description = "The file API")
public class FileController {

  @Autowired
  private final FileService fileService;

  @Operation(summary = "Upload multiple file", security = {
      @SecurityRequirement(name = "bearer-key")})
  @PostMapping(value = {EndpointConst.FILE_UPLOAD}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadMultipleFile(
      @RequestPart("files") @RequestParam("files") List<MultipartFile> files) throws IOException {
    var res = fileService.storeAll(files);
    return ResponseEntity.ok(res);
  }

  @Operation(summary = "Get list of files", security = {@SecurityRequirement(name = "bearer-key")})
  @GetMapping
  public ResponseEntity<?> getAllFile() {
    var res = fileService.getAllFile();
    return ResponseEntity.ok(
        DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, res));
  }

  @Operation(summary = "Get a file with id", security = {@SecurityRequirement(name = "bearer-key")})
  @GetMapping(value = {EndpointConst.FILE_GET_BY_ID})
  public ResponseEntity<?> getFileById(
      @Parameter(description = "File id", required = true) @PathVariable("id") UUID fileId) {
    var res = fileService.getFile(fileId);
    return ResponseEntity.ok()
        .body(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, res));
  }

  @Hidden
  @Operation(summary = "Download a file", security = {@SecurityRequirement(name = "bearer-key")})
  @GetMapping(value = {EndpointConst.FILE_DOWNLOAD})
  public ResponseEntity<?> downloadFile(
      @Parameter(description = "File id", required = true) @PathVariable("id") UUID fileId) {
    var downloadedFile = fileService.getFileById(fileId);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=%s", downloadedFile.getName()))
        .body(downloadedFile.getData());
  }
}
