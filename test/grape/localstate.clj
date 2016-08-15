(ns grape.localstate
  (:require [grape.core :refer :all]
            )
  )

(def x (gts 'example {}))

(def x (rule 'singleNode! [] {:create (pattern (node 'n {:label "n1"}))} x))

(def x (rule 'newEdge! []
             {:create (pattern
                        (node 'n1 {:label "n1"})
                        (node 'n2 {:label "n2"})
                        (edge 'e1 {:src 'n1 :tar 'n2 :label "e1"})
                        )
              }
             x)
  )

(def x (rule 'matchNewEdge! []
             {:read (pattern
                      (node 'nMatch {:label "n1"})
                      )
              :create (pattern
                        (node 'n2 {:label "n2"})
                        (edge 'e1 {:src 'nMatch :tar 'n2 :label "eNew"})
                        )
              }
             x)
  )

(def x (rule 'deleteNode! []
             {:read (pattern
                      (node 'n1 {:label "n1"})
                      )
              :delete ['n1]
              }
             x)
  )

(def x (rule 'deleteAnyNode! []
             {:read (pattern (node 'n1))
              :delete ['n1]
              }
             x)
  )

(def x (rule 'testNACHelper! []
        {:create (pattern
                   ; Makes a triangle
                   (node 'n1 {:label "n1"})
                   (node 'n2 {:label "n2"})
                   (node 'n3 {:label "n3"})
                   (edge 'e1 {:src 'n1 :tar 'n2})
                   (edge 'e2 {:src 'n2 :tar 'n3})
                   (edge 'e3 {:src 'n3 :tar 'n1})
                   )
         }
        x)
)


(def x (rule 'testNAC! []
        {:read   (pattern
                   (node 'n1 {:label "n1"})
                   (NAC
                     ; Do not match a triangle where n1 is part of it.
                     (node 'n2 {:label "n2"})
                     (node 'n3 {:label "n2"})
                     (edge 'e1 {:src 'n1 :tar 'n2})
                     (edge 'e2 {:src 'n2 :tar 'n3})
                     (edge 'e3 {:src 'n3 :tar 'n1})
                     )
                   )
         :delete ['n1]
         }
        x)
)

(def x (rule 'threeNodes? []
             {:read (pattern
                      (node 'n1 {:label "n1"})
                      (node 'n2 {:label "n1"})
                      (node 'n3 {:label "n1"})
                      )
              }
             x
             )
  )

(def x (rule 'paramsTest! ['name]
             {:create (pattern
                      (node 'n1 {:label "n1" :asserts {:name "'&name'"}})
                      )
              }
             x
             )
  )

(def x (rule 'paramsTest2! ['name]
             {:create (pattern
                        (node 'n1 {:label "n1" :asserts {:name "'&name'"}})
                        )
              }
             x
             )
  )

(def x (rule 'transactTestPass! []
             {:create (pattern
                        (node 'n1 {:label "n1"})
                        )
              }
             x
             )
  )

(def x (rule 'transactTestFail! []
             {:read (pattern
                      (node 'n2 {:label "n2"})
                      )
              :create (pattern
                        (node 'n1 {:label "n1"})
                        )
              }
             x
             )
  )



(println x)

(defn doThing []
  (while (attempt x (apl 'deleteAnyNode!)))
  (attempt x (transact
                     (apl 'transactTestPass!)
                     (apl 'transactTestFail!)
                     )
           )
  )

