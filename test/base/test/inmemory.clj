(ns base.test.inmemory
  (:require [base.inmemory :as storage])
  (:use midje.sweet))

(background (before :facts (storage/clear)))

(fact "#set, #get should set resp. get back right value"
  (storage/set :key "value")
  (storage/get :key) => "value")

(fact "#get for missing key should not get any value"
  (storage/get :key) => nil)

(fact "#delete should delete specified key/value pair"
  (storage/set :key "value")
  (storage/delete :key)
  (storage/get :key) => nil)

(fact "#delete should delete only specified key/value pair"
  (storage/set :key "value")
  (storage/set :bar "bar")
  (storage/delete :key)
  (storage/get :bar) => "bar")

(facts "#clear should delete all key/value pairs"
  (storage/set :key "value")
  (storage/set :bar "bar")
  (storage/clear)
  (storage/get :key) => nil
  (storage/get :bar) => nil)

; (facts "same key might be stored with different values to different sets"
;   (def cache (storage/create-set))
;   (def cache2 (storage/create-set))

;   (set :key "value" cache)
;   (set :key "value2" cache2)

;   (get :key cache) => "value"
;   (get :key cache2) => "value2"
; )