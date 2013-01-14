(ns base.inmemory-spec
  (:require [speclj.core :refer :all]
            [base.inmemory :as storage]))

(describe "inmemory storage"
  (before (storage/clear))


  (describe "#set, #get"
    (it "should set key/value pair and return correct value on get"
      (let [key :foo value "bar"]
        (storage/set key value)
        (should= value (storage/get key))))

    (it "should return nil for key which was not created yet"
      (should= nil (storage/get :bar)))

    (it "should store and fetch multiple keys"
      (storage/set :foo "foo")
      (storage/set :bar "bar")

      (should= "foo" (storage/get :foo))
      (should= "bar" (storage/get :bar))))


  (describe "#delete"
    (it "should delete the given key"
      (storage/set :foo "bar")
      (storage/delete :foo)
      (should= nil (storage/get :foo)))

    (it "should delete only given key and leave others as is"
      (storage/set :foo "bar")
      (storage/set :bar "bar")

      (storage/delete :foo)

      (should= nil (storage/get :foo))
      (should= "bar" (storage/get :bar))))


  (describe "#clear"
    (it "should clear all key/value pairs"
      (storage/set :foo "foo")
      (storage/set :bar "bar")

      (storage/clear)

      (should= nil (storage/get :foo))
      (should= nil (storage/get :bar)))))