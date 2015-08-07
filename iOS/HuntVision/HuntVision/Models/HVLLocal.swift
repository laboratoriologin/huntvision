//
//  HVLLocal.swift
//  HuntVision
//
//  Created by Login Informatica on 30/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLLocal: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
    var id        : NSNumber!
    var nome      : NSString?
    var cliente   : NSNumber!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "nome" : "nomeLocal", "cliente" : "cliente"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "LocalEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
    override init!() {
        super.init()
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init(dictionary: dictionaryValue, error: error)
        
        self.cliente = dictionaryValue["cliente"]?["id"] as? NSNumber
        
    }

    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
