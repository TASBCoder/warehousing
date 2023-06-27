package com.work.warehouse.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultReturn<T> implements Serializable{

        private static final long serialVersionUI = 1L;

        private int code;
        private String message;
        private T data;
        private long currentTimeMillis = System.currentTimeMillis();

        public ResultReturn() {
            this.code = 200;
        }

        public ResultReturn(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public ResultReturn(T data) {
            this.code = 200;
            this.data = data;
        }

        public ResultReturn(String message) {
            this.code = 500;
            this.message = message;
        }

        public static <T> ResultReturn<T> fail(String message) {
            return new ResultReturn(message);
        }

        public static <T> ResultReturn<T> fail(Integer code, String message) {
            return new ResultReturn(code, message);
        }

        public static <T> ResultReturn<T> success(T data) {
            return new ResultReturn(data);
        }

        public static <T> ResultReturn<T> success() {
            return new ResultReturn();
        }
}
