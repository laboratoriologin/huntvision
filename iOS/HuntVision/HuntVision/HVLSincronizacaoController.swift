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
import TSMessages
class HVLSincronizacaoController: UIViewController {
    
    var error       : NSError?
    var progressHUD : MBProgressHUD?

    @IBOutlet weak var fecharButton: UIButton!
    
    override func viewDidLoad() {
    
        super.viewDidLoad()
        
         self.fecharButton.alpha = 0
        
        if let objects = HVLUsuarioStore().getAll() {
        
            if(objects.count > 0) {
                
                self.fecharButton.alpha = 1
                
            }
            
        }
        
    }

    @IBAction func fechar(sender: AnyObject) {
        
        self.dismissViewControllerAnimated(true, completion: nil)
        
    }
    @IBAction func sincronizar(sender: AnyObject) {
        
        self.error = nil
        
        var usuario: HVLUsuario
        
        progressHUD = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        
        progressHUD?.labelText = "Sincronizando usuários..."
        
        Alamofire.request(.GET, HVLConstants.usuarioURL).responseJSON {(_, _, result, _) in
            
            if(result != nil) {
                
                var json = JSON(result!)
                
                let users = MTLJSONAdapter.modelsOfClass(HVLUsuarioWrapper.self, fromJSONArray: json.arrayObject, error: nil)
                
                var user: HVLUsuarioWrapper!
                
                let managedObjectContext = (UIApplication.sharedApplication().delegate as! AppDelegate).managedObjectContext
                
                HVLDBUtilStore().clean("UsuarioEntity")
                
                for item in users {
                    
                    user = item as! HVLUsuarioWrapper
                    
                    MTLManagedObjectAdapter.managedObjectFromModel(user.usuario, insertingIntoContext: managedObjectContext, error: &self.error)
                    
                }
                
                self.sincronizarCliente(managedObjectContext!)
                
            } else {
                
                UIAlertView(title: "Sem conexão", message: "Não foi possível se conectar ao servidor, tente novamente, ", delegate: nil, cancelButtonTitle: "OK").show()
                
                self.progressHUD?.hide(true)
                
            }
            
        }
        
    }
    
    func sincronizarCliente(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "Sincronizando clientes..."
        
        Alamofire.request(.GET, HVLConstants.clienteURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let clientes = MTLJSONAdapter.modelsOfClass(HVLClienteWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var cliente: HVLClienteWrapper!
            
            HVLDBUtilStore().clean("ClienteEntity")
            
            for item in clientes {
                
                cliente = item as! HVLClienteWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(cliente.cliente, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
            self.sincronizarLocal(managedObjectContext)
            
        }
        
    }
    
    func sincronizarLocal(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "Sincronizando locais..."
        
        Alamofire.request(.GET, HVLConstants.localURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let locais = MTLJSONAdapter.modelsOfClass(HVLLocalWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var localWrapper: HVLLocalWrapper!
            
            HVLDBUtilStore().clean("LocalEntity")
            
            for item in locais {
                
                localWrapper = item as! HVLLocalWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(localWrapper.local, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
        }
        
        self.sincronizarItem(managedObjectContext)
        
    }
    
    func sincronizarItem(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "Sincronizando itens..."
        
        Alamofire.request(.GET, HVLConstants.itemURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let items = MTLJSONAdapter.modelsOfClass(HVLItemWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var itemWrapper: HVLItemWrapper!
            
            HVLDBUtilStore().clean("ItemEntity")
            
            for item in items {
                
                itemWrapper = item as! HVLItemWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(itemWrapper.item, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
            self.sincronizarItemLocal(managedObjectContext)
            
        }
        
    }
    
    func sincronizarItemLocal(managedObjectContext: NSManagedObjectContext) {
        
        Alamofire.request(.GET, HVLConstants.itemLocalURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let itensLocais = MTLJSONAdapter.modelsOfClass(HVLItemLocalWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var itemLocalWrapper: HVLItemLocalWrapper!
            
            HVLDBUtilStore().clean("ItemLocalEntity")
            
            for item in itensLocais {
                
                itemLocalWrapper = item as! HVLItemLocalWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(itemLocalWrapper.itemLocal, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
        }
        
        self.sincronizarQuestionario(managedObjectContext)
        
    }
    
    func sincronizarQuestionario(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "Sincronizando questionários..."
        
        Alamofire.request(.GET, HVLConstants.questionarioURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let questionarios = MTLJSONAdapter.modelsOfClass(HVLQuestionarioWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var questionarioWrapper: HVLQuestionarioWrapper!
            
            HVLDBUtilStore().clean("QuestionarioEntity")
            
            for item in questionarios {
                
                questionarioWrapper = item as! HVLQuestionarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(questionarioWrapper.questionario, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
        }
        
        self.sincronizarTipoQuestionario(managedObjectContext)
        
    }
    
    func sincronizarTipoQuestionario(managedObjectContext: NSManagedObjectContext) {
        
        Alamofire.request(.GET, HVLConstants.tipoQuestionarioURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let tiposQestionarios = MTLJSONAdapter.modelsOfClass(HVLTipoQuestionarioWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var tipoQuestionarioWrapper: HVLTipoQuestionarioWrapper!
            
            HVLDBUtilStore().clean("TipoQuestionarioEntity")
            
            for item in tiposQestionarios {
                
                tipoQuestionarioWrapper = item as! HVLTipoQuestionarioWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(tipoQuestionarioWrapper.tipoQuestionario, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
            self.sincronizarRespostas(managedObjectContext)
            
        }
        
        
    }
    
    func sincronizarRespostas(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "finalizando respostas..."
        
        Alamofire.request(.GET, HVLConstants.respostaURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let respostas = MTLJSONAdapter.modelsOfClass(HVLRespostaWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var respostaWrapper: HVLRespostaWrapper!
            
            HVLDBUtilStore().clean("RespostaEntity")
            
            for item in respostas {
                
                respostaWrapper = item as! HVLRespostaWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(respostaWrapper.resposta, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
            self.sincronizarAgenda(managedObjectContext)
            
        }
        
    }
    
    func sincronizarAgenda(managedObjectContext: NSManagedObjectContext) {
        
        progressHUD?.labelText = "finalizando sincronização..."
        
        Alamofire.request(.GET, HVLConstants.agendaURL).responseJSON {(_, _, result, _) in
            
            var json = JSON(result!)
            
            let agendas = MTLJSONAdapter.modelsOfClass(HVLAgendaWrapper.self, fromJSONArray: json.arrayObject, error: &self.error)
            
            var agendaWrapper: HVLAgendaWrapper!
            
            HVLDBUtilStore().clean("AgendaEntity")
            
            for item in agendas {
                
                agendaWrapper = item as! HVLAgendaWrapper
                
                MTLManagedObjectAdapter.managedObjectFromModel(agendaWrapper.agenda, insertingIntoContext: managedObjectContext, error: &self.error)
                
            }
            
            self.finalizarSincronizacao(managedObjectContext)
            
        }
        
    }

    func finalizarSincronizacao(managedObjectContext: NSManagedObjectContext) {

        managedObjectContext.save(&self.error)
        
        progressHUD?.hide(true)
        
        if(self.error == nil) {
            
            self.dismissViewControllerAnimated(true, completion: nil)
            
        } else {
            
            TSMessage.showNotificationInViewController(self, title:"Erro na sincronização", subtitle: "Tente novamente", type: TSMessageNotificationType.Error)
            
        }
        
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }

}

