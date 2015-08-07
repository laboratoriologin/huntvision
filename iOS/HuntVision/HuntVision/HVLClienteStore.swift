//
//  HVLClienteStore.swift
//  HuntVision
//
//  Created by Login Informatica on 23/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit

class HVLClienteStore: NSObject {
    
    func getById(id: NSNumber!)  -> HVLClienteEntity? {
        
        let appDelegate: AppDelegate =  UIApplication.sharedApplication().delegate as! AppDelegate
        
        let entityDescription = NSEntityDescription.entityForName("ClienteEntity", inManagedObjectContext: appDelegate.managedObjectContext!)
        
        let request = NSFetchRequest()
        
        request.entity = entityDescription
        
        let pred = NSPredicate(format: "id = %d", argumentArray: [id])
        
        request.predicate = pred
        
        var error: NSError?
        
        if let results = appDelegate.managedObjectContext?.executeFetchRequest(request, error: &error) {
            
            return results[0] as? HVLClienteEntity
            
        }
        
        
        return nil;
        
    }
    
   
}
