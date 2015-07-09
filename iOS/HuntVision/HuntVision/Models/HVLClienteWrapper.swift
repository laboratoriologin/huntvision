//
//  HVLClienteWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 29/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//
import Foundation
import Mantle

class HVLClienteWrapper:  MTLModel, MTLJSONSerializing {
    
    var cliente: HVLCliente!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["cliente" : "cliente"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let clienteDictionary = dictionaryValue["cliente"] as? [NSObject : AnyObject]
        
        if(clienteDictionary != nil) {
            
            self.cliente = MTLJSONAdapter.modelOfClass(HVLCliente.self, fromJSONDictionary: clienteDictionary, error: error) as! HVLCliente;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }

}
