(ns listora.component.rabbitmq
  (:require [com.stuartsierra.component :as component]
            [langohr.core :as rmq]))

(defrecord RabbitMQClient [uri]
  component/Lifecycle
  (start [component]
    (if (:conn component)
      component
      (do (assert uri "No RabbitMQ connection URI.")
          (assoc component :conn (rmq/connect {:uri uri})))))
  (stop [component]
    (when-let [conn (:conn component)]
      (rmq/close conn))
    (dissoc component :conn)))

(defn rabbitmq-client
  "Create a component that opens a connection to RabbitMQ when started. The
  connection is stored under the :conn key.

  Accepts the options:
    :uri - the URI of the RabbitMQ instance"
  [config]
  (map->RabbitMQClient config))
