(ns grape.test-test
  (:require
             [grape.test :refer :all]
             [clojure.test :refer :all]
            )
  )

(defn setup [] (println "setup"))
(defn rule [] (println "rule"))
(defn assertIt [] 1)

(rule 'foobar! [] {:read (pattern )})

(deftest it-should-run (grape-test setup rule assertIt))

(deftest it-should-run-again (grape-test setup rule assertIt))