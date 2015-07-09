//
//  HVLUsuarioStore.swift
//  HuntVision
//
//  Created by Login Informatica on 08/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

class HVLUsuarioStore: NSObject {
    
    func getAll() -> [AnyObject]? {
        
        let appDelegate: AppDelegate =  UIApplication.sharedApplication().delegate as! AppDelegate
        
        let entityDescription = NSEntityDescription.entityForName("UsuarioEntity", inManagedObjectContext: appDelegate.managedObjectContext!)
        
        let request = NSFetchRequest()
        
        request.entity = entityDescription
        
        var error: NSError?
        
        var objects = appDelegate.managedObjectContext?.executeFetchRequest(request, error: &error)
        
        return objects
        
    }
    
    func getByQuery(format predicateFormat: String, argumentArray arguments: [AnyObject]?)  -> [AnyObject]? {
        
        let appDelegate: AppDelegate =  UIApplication.sharedApplication().delegate as! AppDelegate
        
        let entityDescription = NSEntityDescription.entityForName("UsuarioEntity", inManagedObjectContext: appDelegate.managedObjectContext!)
        
        let request = NSFetchRequest()
        
        request.entity = entityDescription
        
        let pred = NSPredicate(format: predicateFormat, argumentArray: arguments)
        
        request.predicate = pred
        
        var error: NSError?
        
        return appDelegate.managedObjectContext?.executeFetchRequest(request, error: &error)
        
    }

}
