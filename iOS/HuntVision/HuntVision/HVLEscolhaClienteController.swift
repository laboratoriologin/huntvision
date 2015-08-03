//
//  HVLEscolhaClienteController.swift
//  HuntVision
//
//  Created by Login Informatica on 28/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
class HVLEscolhaClienteController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var escolhaClienteButton : UIButton!
    @IBOutlet weak var escolhaLocalButton   : UIButton!
    @IBOutlet weak var escolhaSecaoButton   : UIButton!
    @IBOutlet weak var pickerView: UIPickerView!
    @IBOutlet weak var hoverView: UIView!
    @IBOutlet weak var toolBar: UIToolbar!
    
    var pickerCliente = true
    var pickerLocal    = false
    var pickerSecao   = false
    
    var clientes       : [AnyObject]!
    var secoes         : [AnyObject]!
    var locais         : [AnyObject]!
    
    var cliente : HVLClienteEntity?
    var secao    : HVLItemEntity?
    var local   : HVLLocalEntity?
  
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        self.pickerView.dataSource = self

        self.pickerView.delegate = self

        self.clientes = HVLDBUtilStore().getAllWithEntity("ClienteEntity")

        self.locais = HVLDBUtilStore().getAllWithEntity("LocalEntity")
        
        self.secoes  =  HVLDBUtilStore().getAllWithEntity("ItemEntity")
        
    }
    
    //MARK : IBActions
    @IBAction func escolherSecao(sender: AnyObject) {
        
        self.pickerCliente = false
        
        self.pickerSecao = false
        
        self.pickerSecao = true
        
        self.showHover(true)
        
        self.pickerView.reloadAllComponents()
    
    }
    
    @IBAction func escolherLocal(sender: AnyObject) {
        
        self.pickerCliente = false
        
        self.pickerLocal = true
        
        self.pickerSecao = false
        
        self.showHover(true)

        self.pickerView.reloadAllComponents()
        
    }
    
    @IBAction func escolherCliente(sender: AnyObject) {
        
        self.pickerCliente = true
        
        self.pickerSecao = false
        
        self.pickerLocal = false
        
        self.showHover(true)
        
        self.pickerView.reloadAllComponents()
        
    }
    
    @IBAction func selectFromPicker(sender: AnyObject) {
        
        self.showHover(false)
        
        if(self.pickerCliente) {
            
            self.cliente = self.clientes[self.pickerView.selectedRowInComponent(0)] as? HVLClienteEntity
            
            let title = self.cliente!.nome
            
            self.escolhaClienteButton.setTitle(title, forState: UIControlState.Normal)
            
            self.locais = HVLDBUtilStore().getAllWithEntity("LocalEntity")
            
            self.secoes = [AnyObject]()
            
            self.local = nil
            
            self.secao = nil
            
        } else if(self.pickerLocal) {
            
            self.local = self.locais[self.pickerView.selectedRowInComponent(0)] as? HVLLocalEntity
            
            let title = self.local!.nome
            
            self.escolhaLocalButton.setTitle(title, forState: UIControlState.Normal)
            
            self.escolhaSecaoButton.setTitle("Escolher seção", forState: UIControlState.Normal)
            
            self.secao = nil
            
        } else {
            
            self.secao = self.secoes[self.pickerView.selectedRowInComponent(0)] as? HVLItemEntity
            
            let title = self.secao!.descricao
            
            self.escolhaSecaoButton.setTitle(title, forState: UIControlState.Normal)
            
        }
        
    }
   
    // MARK: PickerViewDelegate
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        
        if (self.pickerCliente) {
            
            return self.clientes.count
            
        } else if (self.pickerSecao) {
            
            return self.secoes.count
            
        } else {
            
            return self.locais.count
            
        }
        
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String! {
        
        if(self.pickerCliente) {
            
            return (self.clientes[row] as! HVLClienteEntity).nome
            
        } else if(self.pickerSecao) {
            
            return (self.secoes[row] as! HVLItemEntity).descricao
            
        } else {
            
            return (self.locais[row] as! HVLLocalEntity).nome
            
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    

    func showHover(show: Bool) {
        
        UIView.animateWithDuration(0.2, animations: {
            
            if(show) {
                
                self.pickerView.alpha = 1
                
                self.hoverView.alpha = 0.2
                
                self.toolBar.alpha = 1
                
            } else {
                
                self.pickerView.alpha = 0
                
                self.hoverView.alpha = 0
                
                self.toolBar.alpha = 0
                
            }
            
        })
        
    }
    
}
