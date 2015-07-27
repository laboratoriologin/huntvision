//
//  HVLAgendaDetalheViewController.swift
//  HuntVision
//
//  Created by Login Informatica on 24/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import Alamofire
import MBProgressHUD
import TSMessages
class HVLAgendaDetalheViewController: UIViewController {
    
    var agenda: HVLAgendaEntity!
    var progressHUD : MBProgressHUD?
    @IBOutlet weak var registrarSaidaButton: UIButton!
    @IBOutlet weak var registrarChegadaButton: UIButton!
    @IBOutlet weak var localLabel: UILabel!
    @IBOutlet weak var dataLabel: UILabel!
    @IBOutlet weak var descricaoLabel: UILabel!
    @IBOutlet weak var horarioChegadaLabel: UILabel!
    @IBOutlet weak var horarioSaidaLabel: UILabel!
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        self.title = "Agenda"
        
        self.initComponents()
        
    }
    
    func initComponents() {
        
        self.localLabel.text = "Cliente: \(self.agenda.clienteOBJ!.nome) | \(self.agenda.clienteOBJ!.endereco)"
        
        self.descricaoLabel.text = self.agenda.descricao
        
        let dateFormatter = NSDateFormatter()
        
        dateFormatter.dateFormat = "dd/MM/yyyy HH:mm"
        
        self.dataLabel.text = " Agendado para ".stringByAppendingString(dateFormatter.stringFromDate(self.agenda.dataHora))
        
        if let jaChegou = self.agenda.dataHoraChegada as NSDate? {
            
            self.horarioChegadaLabel.text = " Hora de chegada: ".stringByAppendingString(dateFormatter.stringFromDate(self.agenda.dataHoraChegada))
            
            self.registrarChegadaButton.enabled = false
            
            self.registrarSaidaButton.enabled = true
            
        } else {
            
            self.registrarSaidaButton.enabled = false
            
        }
        
        if let jaSaiu = self.agenda.dataHoraSaida as NSDate? {
            
            self.horarioSaidaLabel.text = " Hora de saída: ".stringByAppendingString(dateFormatter.stringFromDate(self.agenda.dataHoraSaida))
            
            self.registrarSaidaButton.enabled = false
            
        }
        
    }

    
    @IBAction func registrarChegada(sender: AnyObject) {
        
        let dateFormatter = NSDateFormatter()
        
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        
        let parameters = [
            "data_hora_chegada": dateFormatter.stringFromDate(NSDate()),
        ]
        
        var url = "\(HVLConstants.agendaURL)/update_horas/\(self.agenda.id)"
        
        self.progressHUD = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
    
        Alamofire.request(.PUT, url, parameters: parameters, encoding: .URL).responseJSON { request, response, JSON, error in
            
            self.progressHUD?.hide(true)
            
            if(error == nil) {
                
                self.agenda.dataHoraChegada = NSDate()
                
                let delegate =  UIApplication.sharedApplication().delegate as! AppDelegate
                
                delegate.managedObjectContext!.save(nil)
                
                TSMessage.showNotificationInViewController(self, title: "Pronto", subtitle: "Chegada registrada com sucesso", type: TSMessageNotificationType.Success)
                
                self.initComponents()
            
            } else {
                
                TSMessage.showNotificationInViewController(self, title:"Ocorreu um erro", subtitle: "Verifique sua conexão e tente novamente", type: TSMessageNotificationType.Error)
                
            }
            
        }
        
    }
    
    @IBAction func registrarSaida(sender: AnyObject) {
        
        let dateFormatter = NSDateFormatter()
        
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        
        let parameters = [
            "data_hora_chegada": dateFormatter.stringFromDate(agenda.dataHoraChegada),
            "data_hora_saida": dateFormatter.stringFromDate(NSDate())
        ]
        
        var url = "\(HVLConstants.agendaURL)/update_horas/\(self.agenda.id)"
        
        self.progressHUD = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        
        Alamofire.request(.PUT, url, parameters: parameters, encoding: .URL).responseJSON { request, response, JSON, error in
            
            self.progressHUD?.hide(true)
            
            if(error == nil) {
                
                self.agenda.dataHoraSaida = NSDate()
                
                let delegate =  UIApplication.sharedApplication().delegate as! AppDelegate
                
                delegate.managedObjectContext!.save(nil)
                
                TSMessage.showNotificationInViewController(self, title:"Pronto", subtitle: "Saída registrada com sucesso", type: TSMessageNotificationType.Success)
                
                self.initComponents()
                
            } else {
                
                
                TSMessage.showNotificationInViewController(self, title:"Ocorreu um erro", subtitle: "Verifique sua conexão e tente novamente", type: TSMessageNotificationType.Error)
                
            }
            
        }
    
    }

    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}
