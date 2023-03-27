package com.intern.hrmanagementapi.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {
    private static final Logger INFO = LoggerFactory.getLogger("infoLog");
    private static final Logger ERROR = LoggerFactory.getLogger("errorLog");
    private static final Logger WARN = LoggerFactory.getLogger("warnLog");
    private static final Logger DEBUG = LoggerFactory.getLogger("debugLog");
    private static final Logger TRACE = LoggerFactory.getLogger("traceLog");
    private static final Logger ERROR_WORKER = LoggerFactory.getLogger("errorWorkerLog");
    private static final Logger INFO_KAFKA = LoggerFactory.getLogger("infoKafkaLog");

    public static void info(String message) {
        INFO.info(message);
    }

    public static void error(String message) {
        ERROR.error(message);
    }

    public static void warn(String message) {
        WARN.warn(message);
    }

    public static void debug(String message) {
        DEBUG.debug(message);
    }

    public static void trace(String message) {
        TRACE.trace(message);
    }

    public static void errorWorker(String message, Throwable ex) {
        ERROR_WORKER.error(message, ex);
    }

    public static void infoKafka(String message, Throwable ex) {
        INFO_KAFKA.info(message, ex);
    }


}
