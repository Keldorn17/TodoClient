package com.keldorn.todoclient.mapper;

import com.keldorn.todoclient.dto.TodoRequest;
import com.keldorn.todoclient.dto.TodoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoRequest toRequest(TodoResponse response);
}
