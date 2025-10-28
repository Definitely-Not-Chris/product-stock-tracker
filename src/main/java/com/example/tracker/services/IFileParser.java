
package com.example.tracker.services;


import org.springframework.web.multipart.MultipartFile;

public interface IFileParser<T> {
    T parse(MultipartFile file, int start) throws Exception;

    T parse(MultipartFile file) throws Exception;
}
