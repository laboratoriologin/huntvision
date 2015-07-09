//
//  HVLResposta.swift
//  HuntVision
//
//  Created by Login Informatica on 06/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLResposta: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
    var id           : NSNumber!
    var questionario : NSNumber!
    var descricao    : String!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "descricao" : "descricao", "questionario" : "questionario"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "RespostaEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
    override init!() {
        super.init()
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init(dictionary: dictionaryValue, error: error)
        
        let questionarioDictionary = dictionaryValue["questionario"] as? [NSObject : AnyObject]
        
        if(questionarioDictionary != nil) {
            
            self.questionario = (MTLJSONAdapter.modelOfClass(HVLQuestionario.self, fromJSONDictionary: questionarioDictionary, error: error) as! HVLQuestionario).id;
            
        }
      
    }
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
