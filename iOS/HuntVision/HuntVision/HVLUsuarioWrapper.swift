//
//  HVLUsuarioWrapper.swift
//  HuntVision
//
//  Created by Login Informatica on 26/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import Foundation
import Mantle

class HVLUsuarioWrapper: MTLModel, MTLJSONSerializing {
    
    var usuario: HVLUsuario!
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["usuario" : "usuario"]
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init()
        
        let usuarioDictionary = dictionaryValue["usuario"] as? [NSObject : AnyObject]
        
        if(usuarioDictionary != nil) {
            
            self.usuario = MTLJSONAdapter.modelOfClass(HVLUsuario.self, fromJSONDictionary: usuarioDictionary, error: error) as! HVLUsuario;
            
        }
        
    }

    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
}
