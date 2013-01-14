(ns base.inmemory-spec
  (:require [speclj.core :refer :all]
            [base.inmemory :as storage]))

(describe "inmemory storage"
  (before (storage/clear))


  (describe "#set, #get"
    (it "set return value for :foo key"
      (let [key :foo value "bar"]
        (storage/set key value)
        (should= value (storage/get key))))

    (it "shouldnt return value for :bar key"
      (should= nil (storage/get :bar)))

    (it "should store and fetch multiple keys"
      (storage/set :foo "foo")
      (storage/set :bar "bar")

      (should= "foo" (storage/get :foo))
      (should= "bar" (storage/get :bar))))


  (describe "#delete"
    (it "should delete the :foo key"
      (storage/set :foo "bar")
      (storage/delete :foo)
      (should= nil (storage/get :foo)))

    (it "should delete only :foo key if there are others"
      (storage/set :foo "bar")
      (storage/set :bar "bar")

      (storage/delete :foo)

      (should= nil (storage/get :foo))
      (should= "bar" (storage/get :bar))))


  (describe "#clear"
    (it "should clear all values"
      (storage/set :foo "foo")
      (storage/set :bar "bar")

      (storage/clear)

      (should= nil (storage/get :foo))
      (should= nil (storage/get :bar)))))