/*
 * Copyright (C) 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.livespark.flow.api;

import java.util.function.Function;
import java.util.function.Supplier;

public interface AppFlow<INPUT, OUTPUT> {

    <T> AppFlow<INPUT, T> andThen(Step<OUTPUT, T> nextStep);

    <T> AppFlow<INPUT, T> andThen(Function<OUTPUT, T> transformation);

    <T> AppFlow<T, OUTPUT> butFirst(Function<T, INPUT> transformation);

    <T> AppFlow<T, OUTPUT> butFirst(Step<T, INPUT> prevStep);

    <T> AppFlow<INPUT, T> transition(Function<OUTPUT, AppFlow<INPUT, T>> chooser);

    default <T> AppFlow<INPUT, T> andThen(final Supplier<AppFlow<OUTPUT, T>> supplier) {
        return transition( (final OUTPUT output) -> supplier.get().butFirst( ignore -> output ) );
    }

    default <T> AppFlow<INPUT, T> andThen(final AppFlow<OUTPUT, T> nextFlow) {
        return andThen( () -> nextFlow );
    }

}
