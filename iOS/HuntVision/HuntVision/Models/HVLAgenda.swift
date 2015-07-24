//
//  HVLAgenda.swift
//  HuntVision
//
//  Created by Login Informatica on 22/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import Mantle
class HVLAgenda: MTLModel, MTLJSONSerializing, MTLManagedObjectSerializing {
    
     var id                  : NSNumber!
     var cliente             : NSNumber!
     var dataHora            : NSDate!
     var dataHoraChegada     : NSDate?
     var dataHoraSaida       : NSDate?
     var descricao           : String?
     var usuario             : NSNumber!
    
    
    static func JSONKeyPathsByPropertyKey() -> [NSObject : AnyObject]! {
        return ["id" : "id", "cliente" : "cliente", "dataHora" : "dataHora", "dataHoraChegada" : "dataHoraChegada", "dataHoraSaida" : "dataHoraSaida", "descricao" : "descricao", "usuario" : "usuario"]
    }
    
    static func managedObjectEntityName() -> String! {
        return "AgendaEntity"
    }
    
    static func managedObjectKeysByPropertyKey() -> [NSObject : AnyObject]! {
        return JSONKeyPathsByPropertyKey()
    }
    
    override init!() {
        super.init()

        
    }
    
    override init!(dictionary dictionaryValue: [NSObject : AnyObject]!, error: NSErrorPointer) {
        
        super.init(dictionary: dictionaryValue, error: error)
        
        let usuarioDictionary = dictionaryValue["usuario"] as? [NSObject : AnyObject]
        
        if(usuarioDictionary != nil) {
            
            self.usuario = (MTLJSONAdapter.modelOfClass(HVLUsuario.self, fromJSONDictionary: usuarioDictionary, error: error) as! HVLUsuario).id
            
        }
        
        let clienteDictionary = dictionaryValue["cliente"] as? [NSObject : AnyObject]
        
        if(clienteDictionary != nil) {
            
            self.cliente = (MTLJSONAdapter.modelOfClass(HVLCliente.self, fromJSONDictionary: clienteDictionary, error: error) as! HVLCliente).id
            
        }
        
        
    }
    
    static func dataHoraJSONTransformer() -> NSValueTransformer {
        let _forwardBlock: MTLValueTransformerBlock? = { str in
            return self.dateFormatter().dateFromString(str as! String)
        }
        let _reverseBlock: MTLValueTransformerBlock? = { date in
            return self.dateFormatter().stringFromDate(date as! NSDate)
        }
        return MTLValueTransformer.reversibleTransformerWithForwardBlock(_forwardBlock, reverseBlock: _reverseBlock)
    }
    
    static func dataHoraChegadaJSONTransformer() -> NSValueTransformer {
        let _forwardBlock: MTLValueTransformerBlock? = { str in
            return self.dateFormatter().dateFromString(str as! String)
        }
        let _reverseBlock: MTLValueTransformerBlock? = { date in
            return self.dateFormatter().stringFromDate(date as! NSDate)
        }
        return MTLValueTransformer.reversibleTransformerWithForwardBlock(_forwardBlock, reverseBlock: _reverseBlock)
    }
    
    static func dataHoraSaidaJSONTransformer() -> NSValueTransformer {
        let _forwardBlock: MTLValueTransformerBlock? = { str in
            return self.dateFormatter().dateFromString(str as! String)
        }
        let _reverseBlock: MTLValueTransformerBlock? = { date in
            return self.dateFormatter().stringFromDate(date as! NSDate)
        }
        return MTLValueTransformer.reversibleTransformerWithForwardBlock(_forwardBlock, reverseBlock: _reverseBlock)
    }
    
    required init!(coder: NSCoder!) {
        fatalError("init(coder:) has not been implemented")
    }
    
    static func dateFormatter() -> NSDateFormatter! {
        
        let formatter = NSDateFormatter()
        
        formatter.locale = NSLocale(localeIdentifier: "pt_BR")
        
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss.ssss"
        
        return formatter
        
    }
    
}
