(ns grape.localstate
  (:require [grape.core :refer :all]
            )
  )

(def x (gts 'example *ns*))

(def x (rule 'foobar! [] {:create (pattern (node 'n {:label "FOO"}))} x))

(println *ns*)
(println (ns-interns x))
;(clear!)


(defn doThing []
  (println "doThing")
  (println *ns*)
  (println (ns-interns x))
  (println (eval (ns-resolve x 'gragra)))
  (attempt x (apl 'clear!))
  (attempt x (apl 'foobar!))
  (attempt x (apl 'foobar!))
  (attempt x (apl 'foobar!))
  )

