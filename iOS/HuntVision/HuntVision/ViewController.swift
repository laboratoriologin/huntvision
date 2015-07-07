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
import MBProgressHUD

class ViewController: UIViewController {
    
    var error       : NSError?
    var progressHUD : MBProgressHUD?

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func sincronizar(sender: AnyObject) {
        
        var usuario: HVLUsuario
        
        progressHUD = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        
        progressHUD?.labelText = "Sincronizando usuários..."
        
        Alamofire.request(.GET, HVLConstants.usuarioURL).responseJSON {(_, _, result, _) in
                
            var json = JSON(result!)
            
            let users = MTLJSONAdapter.modelsOfClass(HVLUsuarioWrapper.self, fromJSONArray: json.arrayObject, error: nil)
            
            var user: HVLUsuarioWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let userManagedObject = HVLUsuario()
            
            for item in users {
                
                user = item as! HVLUsuarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(user.usuario, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
            self.sincronizarCliente()
            
        }
        
    }
    
    func sincronizarCliente() {
        
        progressHUD?.labelText = "Sincronizando clientes..."
        
        Alamofire.request(.GET, HVLConstants.clienteURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let clientes = MTLJSONAdapter.modelsOfClass(HVLClienteWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var cliente: HVLClienteWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let userManagedObject = HVLCliente()
            
            for item in clientes {
                
                cliente = item as! HVLClienteWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(cliente.cliente, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
            self.sincronizarLocal()
            
        }
        
    }
    
    func sincronizarLocal() {
        
        progressHUD?.labelText = "Sincronizando locais..."
        
        Alamofire.request(.GET, HVLConstants.localURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let locais = MTLJSONAdapter.modelsOfClass(HVLLocalWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var local: HVLLocalWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let userManagedObject = HVLLocal()
            
            for item in locais {
                
                local = item as! HVLLocalWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(local.local, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
        }
        
        self.sincronizarItem()
        
    }
    
    func sincronizarItem() {
        
        progressHUD?.labelText = "Sincronizando itens..."
        
        Alamofire.request(.GET, HVLConstants.itemURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let items = MTLJSONAdapter.modelsOfClass(HVLItemWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var itemWrapper: HVLItemWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let itemManagedObject = HVLItem()
            
            for item in items {
                
                itemWrapper = item as! HVLItemWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(itemWrapper.item, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
            self.sincronizarItemLocal()
            
        }
        
    }
    
    func sincronizarItemLocal() {
        
        Alamofire.request(.GET, HVLConstants.itemLocalURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let itensLocais = MTLJSONAdapter.modelsOfClass(HVLItemLocalWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var itemLocalWrapper: HVLItemLocalWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let itemManagedObject = HVLItemLocal()
            
            for item in itensLocais {
                
                itemLocalWrapper = item as! HVLItemLocalWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(itemLocalWrapper.itemLocal, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
        }
        
        self.sincronizarQuestionario()
        
    }
    
    func sincronizarQuestionario() {
        
        progressHUD?.labelText = "Sincronizando questionários..."
        
        Alamofire.request(.GET, HVLConstants.questionarioURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let questionarios = MTLJSONAdapter.modelsOfClass(HVLQuestionarioWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var questionarioWrapper: HVLQuestionarioWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let itemManagedObject = HVLQuestionario()
            
            for item in questionarios {
                
                questionarioWrapper = item as! HVLQuestionarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(questionarioWrapper.questionario, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
        }
        
        self.sincronizarTipoQuestionario()
        
    }
    
    func sincronizarTipoQuestionario() {
        
        Alamofire.request(.GET, HVLConstants.tipoQuestionarioURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let tiposQestionarios = MTLJSONAdapter.modelsOfClass(HVLTipoQuestionarioWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var tipoQuestionarioWrapper: HVLTipoQuestionarioWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let itemManagedObject = HVLTipoQuestionario()
            
            for item in tiposQestionarios {
                
                tipoQuestionarioWrapper = item as! HVLTipoQuestionarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(tipoQuestionarioWrapper.tipoQuestionario, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
            self.sincronizarRespostas()
            
        }
        
        
    }
    
    func sincronizarRespostas() {
        
        progressHUD?.labelText = "finalizando sincronização..."
        
        Alamofire.request(.GET, HVLConstants.respostaURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let respostas = MTLJSONAdapter.modelsOfClass(HVLRespostaWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var respostaWrapper: HVLRespostaWrapper!
            
            let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
            
            let itemManagedObject = HVLResposta()
            
            for item in respostas {
                
                respostaWrapper = item as! HVLRespostaWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(respostaWrapper.resposta, insertingIntoContext: managedObjectContext, error: &self.error)
                
                managedObjectContext?.save(&self.error)
                
            }
            
            self.finalizarSincronizacao()
            
        }
        
    }

    func finalizarSincronizacao() {
        
        progressHUD?.hide(true)
        
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }

}

