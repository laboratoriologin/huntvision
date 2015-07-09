//
//  ItemLocalWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle

class HVLItemLocalWrapper:  MTLModel, MTLJSONSerializing {
    
    var itemLocal: HVLItemLocal!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["itemLocal" : "itenslocais"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let itemDictionary = dictionaryValue["itemLocal"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.itemLocal = MTLJSONAdapter.modelOfClass(HVLItemLocal.self, fromJSONDictionary: itemDictionary, error: error) as! HVLItemLocal;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
}