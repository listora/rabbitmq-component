# RabbitMQ-Component

A [component][] that holds a [RabbitMQ][] client connection using
[Langohr][]. Starting the component will create the connection, while
stopping the component will close it.

[component]: https://github.com/stuartsierra/component
[RabbitMQ]: http://www.rabbitmq.com/
[Langohr]: http://clojurerabbitmq.info/

## Installation

Add the following dependency to your project.clj file:

```clojure
[listora/rabbitmq-component "0.1.0-SNAPSHOT"]
```

## Usage

Require the library:

```clojure
(require '[listora.component.rabbitmq :refer [rabbitmq-client]]
         '[com.stuartsierra.component :as component])
```

Then create the client component with a URI:

```clojure
(rabbitmq-client {:uri "amqp://localhost:5672"})
```

Starting the component will create a `:conn` key:

```clojure
(-> (rabbitmq-client {:uri "amqp://localhost:5672"})
    (component/start)
    :conn)
```

The connection will be closed and removed when the component is
stopped.

## License

Copyright © 2014 Listora

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
