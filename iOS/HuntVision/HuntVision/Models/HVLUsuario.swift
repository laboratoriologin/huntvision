//
//  HVLUsuario.swift
//  HuntVision
//
//  Created by Login Informatica on 26/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLUsuario: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {

    var id      : NSNumber?
    var email   : NSString?
    var login   : NSString?
    var senha   : NSString?
    var celular : NSString?
    var nome    : NSString?
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "email" : "email", "login" : "login", "senha" : "senha", "celular" : "celular", "nome" : "nome"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "UsuarioEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }

}
