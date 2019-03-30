package com.design.rpg.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author deng
 */
public final class Converter {
    private final static MapperFactory MAPPER_FACTORY;
    private final static MapperFacade MAPPER_FACADE;

    private Converter() {
    }

    static {
        MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();
        MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();
    }

    public static Object map(Object fromObject, Class<?> toClass) {
        return MAPPER_FACADE.map(fromObject, toClass);
    }

    public static void map(Object fromObject, Object toObject) {
        MAPPER_FACADE.map(fromObject, toObject);
    }
}
