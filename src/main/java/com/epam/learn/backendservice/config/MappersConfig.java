package com.epam.learn.backendservice.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.WARN,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    imports = {java.util.UUID.class})
public class MappersConfig {}
