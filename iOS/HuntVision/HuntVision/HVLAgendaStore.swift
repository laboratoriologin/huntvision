//
//  HVLAgendaStore.swift
//  HuntVision
//
//  Created by Login Informatica on 22/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit

class HVLAgendaStore:  NSObject {
    
    func getAll() -> [AnyObject]? {
        
        let appDelegate: AppDelegate =  UIApplication.sharedApplication().delegate as! AppDelegate
        
        let entityDescription = NSEntityDescription.entityForName("AgendaEntity", inManagedObjectContext: appDelegate.managedObjectContext!)
        
        let request = NSFetchRequest()
        
        request.entity = entityDescription
        
        var descriptors = [NSSortDescriptor]()
        
        descriptors.append(NSSortDescriptor(key: "dataHora", ascending: false))

        request.sortDescriptors = descriptors
        
        var error: NSError?
        
        var objects = appDelegate.managedObjectContext?.executeFetchRequest(request, error: &error)
        
        return objects
        
    }
    
}
