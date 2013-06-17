(ns numeric.expresso.test-utils
  (:use numeric.expresso.utils)
  (:use clojure.test)
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic.protocols]
        [clojure.core.logic :exclude [is] :as l]
        clojure.test)
  (:require [clojure.core.logic.fd :as fd]
            [numeric.expressso.construct :as c])
  (:require [clojure.core.logic.unifier :as u]))


#_(deftest test-constant
  (is (= 10 (c/ex 10)))
  (is (constant? (c/ex 1)))
  (is (not (constant? (c/ex (+ 1 X))))))

#_(deftest test-without-symbol
  (is (without-symbol? 'X (c/ex Y)))
  (is (without-symbol? 'X (c/ex (+ 1 Y))))
  (is (not (without-symbol? 'X (c/ex X))))
  (is (not (without-symbol? 'X (c/ex (+ 1 X))))))


(deftest test-lifto-with-inverse
  (let [inco (lifto-with-inverse inc dec)]
    (is (= [3] (run* [q] (inco 2 q))))
    (is (= [2] (run* [q] (inco q 3))))))

#_(deftest test-expo 
  (is (= [1] (run* [q] (expo '+ [q 2]  (ex (+ 1 2))))))
  (is (= [] (run* [q] (expo '- [q 2]  (ex (+ 1 2))))))
  (is (= [[1 2]] (run* [q] (fresh [ex op lhs rhs]
                                  (expo '+ [1 2] ex)
                                  (expo op [lhs rhs] ex)
                                  (== q [lhs rhs]))))))