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


package org.livespark.process.client.local;

import java.util.Optional;
import java.util.function.Function;

class TransformationNode<INPUT, OUTPUT> extends ProcessNode<INPUT, OUTPUT> {

    final Function<INPUT, OUTPUT> transformation;

    TransformationNode( final Function<INPUT, OUTPUT> transformation ) {
        this( transformation, Optional.empty(), Optional.empty() );
    }

    TransformationNode( final Function<INPUT, OUTPUT> transformation,
                        final Optional<ProcessNode<?, INPUT>> prev,
                        final Optional<ProcessNode<OUTPUT, ? >> next ) {
        super( prev, next );
        this.transformation = transformation;
    }

}
