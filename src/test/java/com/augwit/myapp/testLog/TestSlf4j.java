package com.augwit.myapp.testLog;


import com.augwit.myapp.MyApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
@Transactional
public class TestSlf4j {

    @Test
    public void sayHello(){

        Logger logger = LoggerFactory.getLogger(TestSlf4j.class);
        Integer t = 20;
        Integer oldT;



        Integer temperature = 23;

        oldT = t;
        t = temperature;

        Stream<Logger> loggerStream = new Stream<Logger>() {
            @Override
            public Stream<Logger> filter(Predicate<? super Logger> predicate) {
                return null;
            }

            @Override
            public <R> Stream<R> map(Function<? super Logger, ? extends R> mapper) {
                return null;
            }

            @Override
            public IntStream mapToInt(ToIntFunction<? super Logger> mapper) {
                return null;
            }

            @Override
            public LongStream mapToLong(ToLongFunction<? super Logger> mapper) {
                return null;
            }

            @Override
            public DoubleStream mapToDouble(ToDoubleFunction<? super Logger> mapper) {
                return null;
            }

            @Override
            public <R> Stream<R> flatMap(Function<? super Logger, ? extends Stream<? extends R>> mapper) {
                return null;
            }

            @Override
            public IntStream flatMapToInt(Function<? super Logger, ? extends IntStream> mapper) {
                return null;
            }

            @Override
            public LongStream flatMapToLong(Function<? super Logger, ? extends LongStream> mapper) {
                return null;
            }

            @Override
            public DoubleStream flatMapToDouble(Function<? super Logger, ? extends DoubleStream> mapper) {
                return null;
            }

            @Override
            public Stream<Logger> distinct() {
                return null;
            }

            @Override
            public Stream<Logger> sorted() {
                return null;
            }

            @Override
            public Stream<Logger> sorted(Comparator<? super Logger> comparator) {
                return null;
            }

            @Override
            public Stream<Logger> peek(Consumer<? super Logger> action) {
                return null;
            }

            @Override
            public Stream<Logger> limit(long maxSize) {
                return null;
            }

            @Override
            public Stream<Logger> skip(long n) {
                return null;
            }

            @Override
            public void forEach(Consumer<? super Logger> action) {

            }

            @Override
            public void forEachOrdered(Consumer<? super Logger> action) {

            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <A> A[] toArray(IntFunction<A[]> generator) {
                return null;
            }

            @Override
            public Logger reduce(Logger identity, BinaryOperator<Logger> accumulator) {
                return null;
            }

            @Override
            public Optional<Logger> reduce(BinaryOperator<Logger> accumulator) {
                return null;
            }

            @Override
            public <U> U reduce(U identity, BiFunction<U, ? super Logger, U> accumulator, BinaryOperator<U> combiner) {
                return null;
            }

            @Override
            public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super Logger> accumulator, BiConsumer<R, R> combiner) {
                return null;
            }

            @Override
            public <R, A> R collect(Collector<? super Logger, A, R> collector) {
                return null;
            }

            @Override
            public Optional<Logger> min(Comparator<? super Logger> comparator) {
                return null;
            }

            @Override
            public Optional<Logger> max(Comparator<? super Logger> comparator) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public boolean anyMatch(Predicate<? super Logger> predicate) {
                return false;
            }

            @Override
            public boolean allMatch(Predicate<? super Logger> predicate) {
                return false;
            }

            @Override
            public boolean noneMatch(Predicate<? super Logger> predicate) {
                return false;
            }

            @Override
            public Optional<Logger> findFirst() {
                return null;
            }

            @Override
            public Optional<Logger> findAny() {
                return null;
            }

            @Override
            public Iterator<Logger> iterator() {
                return null;
            }

            @Override
            public Spliterator<Logger> spliterator() {
                return null;
            }

            @Override
            public boolean isParallel() {
                return false;
            }

            @Override
            public Stream<Logger> sequential() {
                return null;
            }

            @Override
            public Stream<Logger> parallel() {
                return null;
            }

            @Override
            public Stream<Logger> unordered() {
                return null;
            }

            @Override
            public Stream<Logger> onClose(Runnable closeHandler) {
                return null;
            }

            @Override
            public void close() {

            }
        };

        logger.debug("Temperature set to {}, Old temperature was {}.", t, oldT);
        if (temperature.intValue() < 50){
            logger.info("Temperature has risen above 50 degrees.");
        }
    }
}
