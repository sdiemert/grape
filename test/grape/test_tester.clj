(use 'grape.core)

(rule 'foobar! []
      {:read (pattern (node 'x {:label "X"}))
       :delete ['x]
       }
      )

(rule 'testSetup! [] {
                      :create (pattern (node 'x {:label "X"}))
                      })

(rule 'assertAfter! [] {
                        :read (pattern (NAC (node 'x {:label "X"})))
                        })