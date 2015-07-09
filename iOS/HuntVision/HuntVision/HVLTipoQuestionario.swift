//
//  HVLTipoQuestionario.swift
//  HuntVision
//
//  Created by Login Informatica on 06/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Mantle
class HVLTipoQuestionario: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
    var id        : NSNumber!
    var descricao : NSNumber!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id","descricao" : "descricao"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "TipoQuestionarioEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }}