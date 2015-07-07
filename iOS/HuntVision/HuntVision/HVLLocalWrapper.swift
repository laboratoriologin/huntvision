//
//  HVLLocalWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 30/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle
class HVLLocalWrapper:  MTLModel, MTLJSONSerializing {
    
    var local: HVLLocal!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["local" : "locais"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let localDictionary = dictionaryValue["local"] as? [NSObject : AnyObject]
        
        if(localDictionary != nil) {
            
            self.local = MTLJSONAdapter.modelOfClass(HVLLocal.self, fromJSONDictionary: localDictionary, error: error) as! HVLLocal;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
