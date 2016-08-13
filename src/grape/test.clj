(ns grape.test
  (:require
            [clojure.test :refer :all]
            [grape.core :refer :all]
            )
  )
(defn setup-test [p]
    (println "Initalizing test...")
    (println (ns-name *ns*))
    (gts 'unittest)
    (load-file p)
    )


(defn before []
  ;(in-ns 'grape.test) ;  we need to set the namespace
    (println "before test")
    (println (ns-name *ns*))
    (println (ns-interns *ns*))
    ;(clear!)
  )

(defn after [])

(defn grape-test [path host rule assert]
  ((fn []
     (setup-test path)
     (before)
     (println "in test")
     (println (ns-name *ns*))
     (host)
     (rule)
     (is (assert)) ; condition should return true to is.
     )
    )
  )