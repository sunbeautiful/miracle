package com.miracle.common.mapper;

import java.util.List;

/**
 * @author sqq
 * @description mapstruct基类
 * @date 2021/12/11 11:36
 */
public interface BaseMapper<S, T> {

  T domainToDto(S source);

  List<T> domainToDto(List<S> sources);


}
