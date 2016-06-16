(ns grape.test
  (:require
            [clojure.test :refer :all :as grape]
            [grape.core :refer :all]
            )
  )

(gts 'unittest)

(defn before [])

(defn after [])

(defn grape-test [setup rule assert]
  ((fn []
     (is (run-test setup rule assert))
     ))
  )