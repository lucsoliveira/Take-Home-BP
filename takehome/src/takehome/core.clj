(ns takehome.core
  (:require [takehome.rules :as t.rules]))

(defn can-access? [object purchase]
  ; access rules
  (cond
    (= (:type purchase) :patriota) (t.rules/patriota-rules object purchase) ; Patriota
    (= (:type purchase) :premium) (t.rules/premium-rules object purchase) ; Premium
    (= (:type purchase) :patron) (t.rules/patron-rules object purchase) ; Mecenas
    :else false))
