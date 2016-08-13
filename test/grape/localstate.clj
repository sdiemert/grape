(ns grape.localstate
  (:require [grape.core :refer :all]
            )
  )

(def x (gts 'example {}))

(println x)

(def x (rule 'foobar! [] {:create (pattern (node 'n {:label "FOO"}))} x))

(println x)

;(clear!)


(defn doThing []
  (println "doThing")
  (println *ns*)
  (println x)
  (attempt x (apl 'foobar!))
  (attempt x (apl 'foobar!))
  (attempt x (apl 'foobar!))
  )

