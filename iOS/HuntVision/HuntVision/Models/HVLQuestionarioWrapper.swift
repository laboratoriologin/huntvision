//
//  HVLQuestionarioWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle

class HVLQuestionarioWrapper:  MTLModel, MTLJSONSerializing {
    
    var questionario: HVLQuestionario!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["questionario" : "questionarios"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let itemDictionary = dictionaryValue["questionario"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.questionario = MTLJSONAdapter.modelOfClass(HVLQuestionario.self, fromJSONDictionary: itemDictionary, error: error) as! HVLQuestionario;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
}