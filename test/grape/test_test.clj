(ns grape.test-test
  (:require
             [grape.test :refer :all]
             [clojure.test :refer :all]
            )
  )

(defn setup [] (println "setup"))
(defn ruleit [] (println "rule"))
(defn assertIt [] 1)

;(rule 'foobar! [] {:read (pattern )})
;(setup-test)

(deftest it-should-run (grape-test "test/grape/test_tester.clj" 'testSetup! 'foobar! 'assertAfter!))

;(deftest it-should-run-again (grape-test setup ruleit assertIt))