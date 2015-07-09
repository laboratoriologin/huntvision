//
//  HVLRespostaWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 06/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle

class HVLRespostaWrapper:  MTLModel, MTLJSONSerializing {
    
    var resposta: HVLResposta!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["resposta" : "respostas"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let itemDictionary = dictionaryValue["resposta"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.resposta = MTLJSONAdapter.modelOfClass(HVLResposta.self, fromJSONDictionary: itemDictionary, error: error) as! HVLResposta;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
}