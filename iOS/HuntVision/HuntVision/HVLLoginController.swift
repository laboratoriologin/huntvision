//
//  HVLLoginController.swift
//  HuntVision
//
//  Created by Login Informatica on 07/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import TSMessages
class HVLLoginController: UIViewController {

    @IBOutlet weak var loginTextField: UITextField!
    @IBOutlet weak var senhaTextField: UITextField!

    
    override func viewDidAppear(animated: Bool) {
        
        super.viewDidAppear(animated)
        
        if let results = HVLUsuarioStore().getAll() {
            
            if results.count == 0 {
                
                self.performSegueWithIdentifier("sg_sincronizacao", sender: nil)
                
            }
            
        }
        
    }
    
    @IBAction func entrar(sender: AnyObject) {
        
        if(self.loginTextField.text.isEmpty || self.senhaTextField.text.isEmpty) {
            
                TSMessage.showNotificationWithTitle("Validação", subtitle: "Preencha todos os campos para se autenticar", type: TSMessageNotificationType.Error)

            return
            
        }
        
        if let results = HVLUsuarioStore().getByQuery(format: "(login = %@ and senha = %@)", argumentArray: [loginTextField.text, senhaTextField.text.md5()]) {
            
            if results.count > 0 {
               
                let match = results[0] as! NSManagedObject
                
                Utils.setUser(match.valueForKey("id") as! NSNumber)
                
                self.performSegueWithIdentifier("sg_menu", sender:nil)
          
            } else {
            
                TSMessage.showNotificationWithTitle("Não autorizado", subtitle: "Usuário inválido", type: TSMessageNotificationType.Error)
            
            }
            
        }
        
    }
    
    
    override func touchesBegan(touches: Set<NSObject>, withEvent event: UIEvent) {

        if(loginTextField.isFirstResponder()) {
            loginTextField.resignFirstResponder()
        }
        
        if(senhaTextField.isFirstResponder()) {
            senhaTextField.resignFirstResponder()
        }

    }
    
}
