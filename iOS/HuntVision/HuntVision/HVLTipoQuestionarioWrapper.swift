//
//  HVLTipoQuestionarioWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 06/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//
import Foundation
import Mantle

class HVLTipoQuestionarioWrapper:  MTLModel, MTLJSONSerializing {
    
    var tipoQuestionario: HVLTipoQuestionario!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["tipoQuestionario" : "tipoquestionarios"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let itemDictionary = dictionaryValue["tipoQuestionario"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.tipoQuestionario = MTLJSONAdapter.modelOfClass(HVLTipoQuestionario.self, fromJSONDictionary: itemDictionary, error: error) as! HVLTipoQuestionario;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
}