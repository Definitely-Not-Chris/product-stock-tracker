
package com.example.tracker.services;



import java.io.Reader;

public interface IFileParser<T> {
    T parse(Reader reader, int start) throws Exception;

    T parse(Reader reader) throws Exception;
}
