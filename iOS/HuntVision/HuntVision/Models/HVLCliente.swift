//
//  HVLCliente.swift
//  HuntVision
//
//  Created by Login Informatica on 29/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Mantle
class HVLCliente: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {

    var id       : NSNumber?
    var email    : NSString?
    var celular  : NSString?
    var nome     : NSString?
    var cnpj     : NSString?
    var endereco : NSString?
    var cidade   : NSString?
    var bairro   : NSString?
    var estado   : NSString?
    var cep      : NSString?
    var pais     : NSString?
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "email" : "email", "celular" : "celular", "nome" : "nome", "cnpj" : "cnpj", "endereco" : "endereco", "cidade" : "cidade"
            , "bairro" : "bairro", "estado" : "estado", "cep" : "cep", "pais" : "pais"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "ClienteEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
}