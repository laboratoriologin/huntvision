//
//  HVLAgendaWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 22/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import Mantle
class HVLAgendaWrapper:MTLModel, MTLJSONSerializing {
    
    var agenda: HVLAgenda!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["agenda" : "agendas"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let localDictionary = dictionaryValue["agenda"] as? [NSObject : AnyObject]
        
        if(localDictionary != nil) {
            
            self.agenda = MTLJSONAdapter.modelOfClass(HVLAgenda.self, fromJSONDictionary: localDictionary, error: error) as! HVLAgenda;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
}
    