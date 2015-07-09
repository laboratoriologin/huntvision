//
//  HVLItem.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLItem: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
    var id        : NSNumber!
    var chave     : NSString?
    var descricao : NSString?
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "chave" : "chave", "descricao" : "descricao"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "ItemEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
}