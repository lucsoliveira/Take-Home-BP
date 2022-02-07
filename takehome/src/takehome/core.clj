(ns takehome.core
  (:require [takehome.rules :as t.rules]))

(defn can-access? [object purchase]
  ; access rules
  (cond
    (= (:type purchase) :patriota) (t.rules/patriota-rules object purchase) ; Patriota
    :else false))
