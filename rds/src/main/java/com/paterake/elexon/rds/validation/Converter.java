package com.paterake.elexon.rds.validation;

/**
 * Converts an chosen Input type to a chosen Output type
 * @param <I> Input type to be converted
 * @param <O> Converted output type
 */
@FunctionalInterface
public interface Converter<I, O> {
    O convert(I i);
}
