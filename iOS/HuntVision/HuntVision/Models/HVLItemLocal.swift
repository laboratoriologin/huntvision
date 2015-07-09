//
//  HVLItemLocal.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLItemLocal: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
    var id    : NSNumber!
    var item  : NSNumber!
    var local : NSNumber!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "item" : "item", "local" : "local"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "ItemLocalEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
    override init!() {
        super.init()
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init(dictionary: dictionaryValue, error: error)
        
        let itemDictionary = dictionaryValue["item"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.item = (MTLJSONAdapter.modelOfClass(HVLItem.self, fromJSONDictionary: itemDictionary, error: error) as! HVLItem).id;
            
        }
        
        let localDictionary = dictionaryValue["local"] as? [NSObject : AnyObject]
        
        if(localDictionary != nil) {
            
            self.local = (MTLJSONAdapter.modelOfClass(HVLLocal.self, fromJSONDictionary: localDictionary, error: error) as! HVLLocal).id;
            
        }
        
    }
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
