//
//  HVLItemWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import Mantle

class HVLItemWrapper:  MTLModel, MTLJSONSerializing {
    
    var item: HVLItem!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["item" : "item"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let itemDictionary = dictionaryValue["item"] as? [NSObject : AnyObject]
        
        if(itemDictionary != nil) {
            
            self.item = MTLJSONAdapter.modelOfClass(HVLItem.self, fromJSONDictionary: itemDictionary, error: error) as! HVLItem;
            
        }
        
    }
    
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
