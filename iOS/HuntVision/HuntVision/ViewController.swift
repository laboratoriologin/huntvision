//
//  ViewController.swift
//  HuntVision
//
//  Created by Login Informatica on 25/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import Alamofire
import Mantle
class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
       
    }

    @IBAction func sincronizar(sender: AnyObject) {
        
        var usuario: HVLUsuario
        
        var error: NSError?
        
        Alamofire.request(.GET, HVLConstants.usuarioURL).responseJSON {(_, _, result, _) in
                
            var json = JSON(result!)
            
            let users = MTLJSONAdapter.modelsOfClass(HVLUsuarioWrapper.self, fromJSONArray: json.arrayObject, error: nil)
            
            var user: HVLUsuarioWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            var error: NSError?
            
            let userManagedObject = HVLUsuario()
            
            for item in users {
                
                user = item as! HVLUsuarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(user.usuario, insertingIntoContext: managedObjectContext, error: &error)
                
                managedObjectContext?.save(&error)
                
            }
            
            self.sincronizarCliente()
            
        }
        
    }
    
    func sincronizarCliente() {
        
        var error: NSError?
        
        Alamofire.request(.GET, HVLConstants.clienteURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let clientes = MTLJSONAdapter.modelsOfClass(HVLClienteWrapper.self, fromJSONArray: json.arrayObject, error: nil)
            
            var cliente: HVLClienteWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let userManagedObject = HVLCliente()
            
            for item in clientes {
                
                cliente = item as! HVLClienteWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(cliente.cliente, insertingIntoContext: managedObjectContext, error: &error)
                
                managedObjectContext?.save(&error)
                
            }
            
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }

}

