(ns listora.component.rabbitmq-test
  (:require [clojure.test :refer :all]
            [com.stuartsierra.component :as component]
            [langohr.core :as rmq]
            [listora.component.rabbitmq :refer :all]))

;; These tests assume RabbitMQ is started and listening on port 5672

(deftest test-rabbitmq-client
  (let [component (rabbitmq-client {:uri "amqp://localhost:5672"})]
    (testing "initial values"
      (is (= (:uri component) "amqp://localhost:5672"))
      (is (nil? (:conn component))))
    (testing "start and stop"
      (let [component (component/start component)]
        (is (:conn component))
        (is (rmq/open? (:conn component)))
        (let [conn      (:conn component)
              component (component/stop component)]
          (is (nil? (:conn component)))
          (is (rmq/closed? conn)))))
    (testing "no URL"
      (is (thrown? AssertionError (component/start (rabbitmq-client {})))))))
